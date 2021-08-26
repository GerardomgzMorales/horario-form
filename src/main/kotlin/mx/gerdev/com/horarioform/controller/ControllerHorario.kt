package mx.gerdev.com.horarioform.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ControllerHorario {



    @GetMapping("/", "", "/index")
    fun index(modelo: Model): String {

        modelo.addAttribute("titulo", "Bienvbenido a la vista")

        return "index";
    }


    @GetMapping("/cerrado")
    fun cerrado(modelo: Model): String {
        modelo.addAttribute("titulo", "No hya atencion")
        modelo.addAttribute("mensaje", "hasta mañana a las 14 hrs")
        return "cerrado"
    }

}