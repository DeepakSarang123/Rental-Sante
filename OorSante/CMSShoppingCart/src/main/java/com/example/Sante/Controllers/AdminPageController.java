package com.example.Sante.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.Sante.models.PageRepository;
import com.example.Sante.models.data.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static javax.swing.JOptionPane.showMessageDialog;

@Controller
@RequestMapping("/admin/pages")
public class AdminPageController {

    @Autowired
    private PageRepository pageRepo;

    /*
     * public AdminPageController(PageRepository pageRepo) { this.pageRepo =
     * pageRepo; }
     */

    @GetMapping
    public String index(Model model) {

        List<Page> pages = pageRepo.findAll();

        model.addAttribute("pages", pages);

        return "/admin/pages/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Page page) {

        return "/admin/pages/add";
    }

    @PostMapping("/add")
    public String add(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Model model) {

        /* Boolean bool = bindingResult.hasErrors();
        showMessageDialog(null, bool); */
        if (bindingResult.hasErrors()) {
            return "/admin/pages/add";
        }

        redirectAttributes.addFlashAttribute("message", "Page added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-")
                : page.getSlug().toLowerCase().replace(" ", "-");

        Page slugExists = pageRepo.findBySlug(slug);

        if (slugExists != null) {
            redirectAttributes.addFlashAttribute("message", "Slug exists, add another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("page", page);
        } else {
            page.setSlug(slug);
            page.setSorting(100);

            pageRepo.save(page);
        }
        return "redirect:/admin/pages/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){

        Page page = pageRepo.getOne(id);
        model.addAttribute("page", page);
        return "/admin/pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Model model) {

        /* Boolean bool = bindingResult.hasErrors();
        showMessageDialog(null, bool); */
        if (bindingResult.hasErrors()) {
            return "/admin/pages/edit";
        }

        redirectAttributes.addFlashAttribute("message", "Page edited");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-")
                : page.getSlug().toLowerCase().replace(" ", "-");

        Page slugExists = pageRepo.findBySlug(page.getId(), slug);

        if (slugExists != null) {
            redirectAttributes.addFlashAttribute("message", "Slug exists, add another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("page", page);
        } else {
            page.setSlug(slug);
            page.setSorting(100);

            pageRepo.save(page);
        }
        return "redirect:/admin/pages/edit/" + page.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){

        pageRepo.deleteById(id);
        return "/admin/pages/index";
    }

}