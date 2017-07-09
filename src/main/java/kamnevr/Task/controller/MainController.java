package kamnevr.Task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Владимир on 09.07.2017.
 */
@Controller
public class MainController {
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */

    public static Long d = 0L;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        //здесь же надо запускать потоки и получать результат, а затем этот результат возвращать на клиент


        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-user">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/report")
    public ModelAndView formReport(
            //@ModelAttribute("userJSP") User user
    ) {
        ModelAndView modelAndView = new ModelAndView();

        //имя представления, куда нужно будет перейти
        modelAndView.setViewName("page");

        //записываем в атрибут userJSP (используется на странице *.jsp объект user
        //modelAndView.addObject("userJSP", user);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }
}
