package pe.com.innovaviajes.data.ivjpadestinos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.data.ivjpadestinos.dao.AeropuertoWebRepository;

@RestController
@RequestMapping(value = "/AeropuertoWebJPAService")
public class AeropuertoWebController {

	private static final Logger log = LoggerFactory.getLogger(AeropuertoWebController.class);

	@Autowired
	private AeropuertoWebRepository aeropuertoWebRepository;

}
