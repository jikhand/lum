package com.set.config;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.set.utils.CommonUtils;

@PropertySource("classpath:db.properties")
@Component
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.set") })
public class DomainRequestFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(DomainRequestFilter.class);

	private SchoolIdentification schoolIdentification;
	@Autowired
	private Environment env;

	@Autowired
	private SessionFactory sessionFactory;

	private String strSqlUrl = "";

	public DomainRequestFilter() {
		super();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		schoolIdentification = new SchoolIdentification();
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String uri = httpRequest.getRequestURL().toString();
		if (uri.indexOf(uri.indexOf("164.164.34.87")) != -1) {
			uri.replace("164.164.34.87", "lu-luschool.com");
		}
		if (uri.indexOf(uri.indexOf("localhost")) != -1) {
			uri.replace("localhost", "lu-luschool.com");
		}
		LOGGER.debug("Domain request received -- " + uri);
		String schoolName = getSchoolName(uri);
		schoolIdentification.setSchoolName(schoolName);
		schoolIdentification.setSchoolDb(schoolName.toLowerCase());
		try {
			// tenantConfigRepo.reload();

			if (schoolName != null) {
				if (CommonUtils.isSchoolActive(uri, schoolName)) {
					try {
						// tenantConfigRepo.reload();
						setDbProperty(schoolIdentification.getSchoolDb());

						session.setAttribute("schoolIdentification", schoolIdentification);
					} catch (Exception e) {
						throw new IOException(e);
					}
				}

				schoolIdentification.setSchoolName(schoolName);
				filterChain.doFilter(request, response);
				// httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.print("<html><head>Error Page</head><body>");
				out.print("<div><hr></hr><div><h2>Module Error</h2><h4>Module not found</h4>");
				out.print("<div><div><p>Invalid module. Please contact Tenant Administrator!!</p></div>");
				out.print("</div></div><hr></hr></div></body></html>");
				out.close();
			} else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.print("<html><head>Error Page</head><body>");
				out.print("<div><hr></hr><div><h2> School Error</h2><h4>In Active</h4>");
				out.print("<div><div><p>Tenant is Inactive. Please contact Tenant Administrator!!</p></div>");
				out.print("</div></div><hr></hr></div></body></html>");
				out.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void setDbProperty(String schoolDb) throws IOException {
		Properties props = System.getProperties();
		String dbHost = env.getProperty("host.url");
		String sqlUrlPreffix = "jdbc:mysql://"+dbHost+":3306/";
		String sqlUrlSuffix = "?useSSL=false";
		String sqlUrl = sqlUrlPreffix + schoolDb + sqlUrlSuffix;
		props.setProperty("mysql.url", sqlUrl);
		strSqlUrl = sqlUrl;
		sessionFactory.getProperties().put(URL, strSqlUrl);
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
	}

	private String getSchoolName(String uri) {
		String schoolName = "luschool";
		
		if (uri.indexOf("lu-") != -1) {
			String uriSubstring = uri.substring(uri.indexOf("lu") + 3, uri.indexOf(".com"));
			schoolName = uriSubstring;
		}
		return schoolName;
	}
}
