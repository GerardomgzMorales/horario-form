package mx.gerdev.com.horarioform.interceptor

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component("horarioInterceptor")
class HorarioInterceptor : HandlerInterceptor {

    @Value("\${config.horario.inicio}")
    private val horaInicio: Int? = null

    @Value("\${config.horario.fin}")
    private val horaFin: Int? = null

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val horaActual: Calendar? = Calendar.getInstance()
        val horaDelDia: Int? = horaActual?.get(Calendar.HOUR_OF_DAY)

        if (horaDelDia != null && horaFin != null && horaInicio != null) {
            if (horaDelDia >= horaInicio && horaFin < horaDelDia) {

                val mensaje = StringBuilder("Bienvenido a la atención de los clientes")
                mensaje.append(", atendemos desde las ")
                mensaje.append("$horaInicio hrs hasta el cierre que es $horaFin hrs")
                mensaje.append("Gracias a la visita. ")

                request.setAttribute("mensaje", mensaje.toString())

                return true
            }
        }
        response.sendRedirect("${request.contextPath}/cerrado")
        return false
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {


        if (handler is HandlerMethod) {
            modelAndView?.let { it.addObject("horario", request.getAttribute("mensaje") as String) }
        }
    }
}