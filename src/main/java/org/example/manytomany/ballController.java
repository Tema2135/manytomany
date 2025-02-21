package org.example.manytomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@org.springframework.stereotype.Controller
public class ballController {
    @Autowired
    private stadiumRepository stadiumRepository;

    @Autowired
    private  teamRepository teamRepository;

    @GetMapping("/home")
    public String home(Model model){
        List<Stadium> stadiums = stadiumRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("stadiums", stadiums);
        model.addAttribute("teams", teams);
        return "homePage";
    }

    @PostMapping("/home/createT")
    public String createT(@ModelAttribute("nameT") String name,
                          @ModelAttribute("number") int number){
        Team team = new Team();
        team.setName(name);
        team.setNumPlayers(number);
        teamRepository.save(team);
        return "redirect:/home";
    }

    @PostMapping("/home/createS")
    public String createS(@ModelAttribute("nameS") String name){
        Stadium stadium = new Stadium();
        stadium.setName(name);
        stadiumRepository.save(stadium);
        return "redirect:/home";
    }


    @PostMapping("/home/connect")
    public String connect(@ModelAttribute("stadium") String nameS,
                          @ModelAttribute("team1") String nameT1,
                          @ModelAttribute("team2") String nameT2){
        Optional<Stadium> stadiumOpt = stadiumRepository.findByName(nameS);
        Optional<Team> team1Opt = teamRepository.findByName(nameT1);
        Optional<Team> team2Opt = teamRepository.findByName(nameT2);
        if (stadiumOpt.isPresent() && team1Opt.isPresent() && team2Opt.isPresent()) {
            Stadium stadium = stadiumOpt.get();
            Team team1 = team1Opt.get();
            Team team2 = team2Opt.get();
            stadium.getTeams().add(team1);
            stadium.getTeams().add(team2);
            team1.getStadiums().add(stadium);
            team2.getStadiums().add(stadium);

            teamRepository.save(team1);
            teamRepository.save(team2);
            stadiumRepository.save(stadium);
            return "redirect:/home";
        }
        return "redirect:/home?Error";
    }

    @Autowired
    private model1Repository model1repository;

    @Autowired
    private model2Repository model2repository;

    @GetMapping("/model")
    public String mainPage(Model model){
        List<Model1> models1=model1repository.findAll();
        List<Model2> models2=model2repository.findAll();
        model.addAttribute("models1",models1);
        model.addAttribute("models2",models2);
        return "mainPage";
    }
}
