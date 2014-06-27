package org.mifosplatform.portfolio.cliententitydetails.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.cliententity.data.ClientEntityDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientEntityDetailsReadPlatformServiceImpl implements
		ClientEntityDetailsReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	
	@Autowired
	public ClientEntityDetailsReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	@Override
	public FileData downloadFile(Long fileId) {
		final String sql = "select file_name as fileName, file_type as contentType, file_location as fileLocation from client_entity_details where id=?";
		FileDownloadMapper mapper = new FileDownloadMapper();
		return jdbcTemplate.queryForObject(sql,mapper,new Object[]{fileId});
	}
	
	private static final class FileDownloadMapper implements RowMapper<FileData>{
		@Override
		public FileData mapRow(ResultSet rs, int rowNum) throws SQLException {
			final String fileName = rs.getString("fileName");
			final String contentType = rs.getString("contentType");
			final String fileLocation = rs.getString("fileLocation");
			return new FileData(new File(fileLocation), fileName, contentType);
		}
	}

	@Override
	public List<ClientEntityDetailsData> retriveEntityDetails(Long clientId) {
		final String sql = "select c.id as id, c.client_id as clientId, c.registered_name as registeredName,c.date_of_incorporation as dateOfIncorporation, c.trading_name as tradingName,c.file_name as fileName from client_entity_details c where client_id=? order by id";
		EntityDetailsMapper mapper = new EntityDetailsMapper();
		return jdbcTemplate.query(sql, mapper,new Object[]{clientId});
	}

		
	private final static class EntityDetailsMapper implements RowMapper<ClientEntityDetailsData>{
		
		@Override
		public ClientEntityDetailsData mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final Long id = rs.getLong("id");
			final String registeredName = rs.getString("registeredName");
			final LocalDate dateOfIncorporation = JdbcSupport.getLocalDate(rs, "dateOfIncorporation");
			final String tradingName = rs.getString("tradingName");
			final String fileName = rs.getString("fileName");
			return new ClientEntityDetailsData(id,registeredName,dateOfIncorporation,tradingName,fileName);
		}
	}
}
