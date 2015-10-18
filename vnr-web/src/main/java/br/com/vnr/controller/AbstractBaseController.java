package br.com.vnr.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class AbstractBaseController extends HttpServlet {

	private static final long serialVersionUID = -6627794965787948425L;

	private static final String JSP_MAIN_FOLDER = "/views";

	protected static Logger LOGGER = Logger.getLogger(AbstractBaseController.class);

	public AbstractBaseController() {
		LOGGER = Logger.getLogger(this.getClass());
	}

	public abstract void initializeScreen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public void forwardToPage(String pathToPage, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(JSP_MAIN_FOLDER.concat(pathToPage))
				.forward(request, response);

	}

	public void forwardToErrorPage(Exception exception, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.forwardWithAttribute("internalException", exception.getMessage(),
				"/views/default/error-page.jsp", request, response);

	}

	public void forwardToMessagePage(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.forwardWithAttribute("message", message, "/views/default/message.jsp", request,
				response);

	}

	public void forwardDataBean(Object bean, String path, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.forwardWithAttribute("databean", bean, path, request, response);

	}

	public void forwardWithAttribute(String attrName, Object attrValue, String path,
			HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		request.setAttribute(attrName, attrValue);
		this.getServletContext().getRequestDispatcher(path).forward(request, response);

	}

	public Object loadBean(String beanName) {
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext());
		return context.getBean(beanName);
	}

	public Object getParameter(HttpServletRequest request, String parameterName)
			throws ServletException {

		Object result = null;

		if (parameterName != null | !parameterName.isEmpty()) {

			result = request.getParameter(parameterName);

			if (result == null) {
				throw new ServletException("O parâmetro informado não existe: " + parameterName);
			}

			return result;

		}

		return result;

	}
}
