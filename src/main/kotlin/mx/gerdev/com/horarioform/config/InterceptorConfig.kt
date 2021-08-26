package mx.gerdev.com.horarioform.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig : WebMvcConfigurer {

    @Autowired
    @Qualifier("horarioInterceptor")
    private var horarioInter: HandlerInterceptor? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        horarioInter?.let { registry.addInterceptor(it).excludePathPatterns("/cerrado") }
    }


}