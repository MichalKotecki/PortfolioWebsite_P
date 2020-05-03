package com.portfolio.portfolio.portfolio.Controller;

import com.portfolio.portfolio.portfolio.Models.Project;
import com.portfolio.portfolio.portfolio.Models.Technology;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PortfolioController {

    Localhost connection:
    String url ="localhost:8080";
    // Heroku connection:

    @GetMapping("/index")
    public String portfolioStart(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // TODO This probably can be done better
        ResponseEntity<Project[]> responseProjects =  restTemplate.exchange("http://" + url + "/api/v1/projects", HttpMethod.GET, entity, Project[].class);
        List<Project> projectList = Arrays.asList(responseProjects.getBody());

       if(projectList != null)
       {
       model.addAttribute("projectList", projectList);
       }
       else
       {
           System.out.println("Project List is probably null");
       }

        ResponseEntity<Technology[]> responseTechnologies = restTemplate.exchange("http://" + url + "/api/v1/technologies", HttpMethod.GET, entity, Technology[].class);
        List<Technology> technologyList = Arrays.asList(responseTechnologies.getBody());

        if(technologyList != null)
        {
            model.addAttribute("technologyList", technologyList);
        }
        else
        {
            System.out.println("Technology List is probably null");
        }

        return "index";
    }


    @GetMapping("/index/{filteredTechnologyID}")
    public String portfolioStart(@PathVariable(value = "filteredTechnologyID") Long filteredTechnologyID, Model model) {

        // http://localhost:8080/api/v1/projects"

        String host = InetAddress.getLoopbackAddress().getHostName();

        if(filteredTechnologyID != null)
        {
            System.out.print("filteredTechnologyID: "); System.out.println(filteredTechnologyID);
        }
        else
        {
            System.out.print("filteredTechnologyID jest nullem.");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        // TO-DO This probably can be done better
        ResponseEntity<Project[]> responseProjects = restTemplate.exchange("http://" + url + "/api/v1/projects", HttpMethod.GET, entity, Project[].class);
        List<Project> projectList = Arrays.asList(responseProjects.getBody());
        projectList = projectList.stream().filter(project -> project.getTechnologyList().stream().anyMatch(technology -> technology.getTechnologyID() == filteredTechnologyID)).collect(Collectors.toList());


        if(projectList != null)
        {
            model.addAttribute("projectList", projectList);
        }
        else
        {
            System.out.println("Project List is probably null");
        }


        ResponseEntity<Technology[]> responseTechnologies = restTemplate.exchange("http://" + url + "/api/v1/technologies", HttpMethod.GET, entity, Technology[].class);
        List<Technology> technologyList = Arrays.asList(responseTechnologies.getBody());

        if(technologyList != null)
        {
            model.addAttribute("technologyList", technologyList);
        }
        else
        {
            System.out.println("Technology List is probably null");
        }

        model.addAttribute("isFilter", "true");     //  makes button NO FILTER appear

        return "index";
    }

    @GetMapping("/")
    public RedirectView LaunchRedirect(RedirectAttributes attributes) {
        System.out.println("This is a launch");
//        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("/index");
    }
}