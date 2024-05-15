<h1 align="center">
<b>Desarrollador de Software</b>
</h1>

¡Hola! 👋 Soy un estudiante apasionado por el desarrollo de software. Actualmente, curso el 6to ciclo de Ingeniería de Sistemas 💻 y me enfoco en el backend y frontend. Siempre estoy buscando aprender y mejorar mis habilidades de programación 🚀

<br>


<div align="center">
  <img src="https://img.shields.io/badge/-HTML-FF5733?style=for-the-badge&logo=html5&logoColor=FF5733&labelColor=282828" alt="html">
<img src="https://img.shields.io/badge/-CSS-264DE4?style=for-the-badge&logo=css3&logoColor=264DE4&labelColor=282828" alt="css">
<img src="https://img.shields.io/badge/-Java-F89820?style=for-the-badge&logo=java&logoColor=F89820&labelColor=282828" alt="java">
<img src="https://img.shields.io/badge/-Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=6DB33F&labelColor=282828" alt="spring">
<img src="https://img.shields.io/badge/-Astro-FF5D01?style=for-the-badge&logo=astro&logoColor=white&labelColor=282828" alt="astro">
<img src="https://img.shields.io/badge/-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white&labelColor=282828" alt="mysql">
<img src="https://img.shields.io/badge/-Tailwind%20CSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white&labelColor=282828" alt="tailwindcss">
<img src="https://img.shields.io/badge/-Git-F05032?style=for-the-badge&logo=git&logoColor=white&labelColor=282828" alt="git">
<img src="https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github&logoColor=white&labelColor=282828" alt="github">
</div>


```java
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
@Builder
@Slf4j
class Developer() {
    private String name;
    private String lastName;
    private String location;
    private String web;
    
    public static void main(String[] args) {
        Developer dev = Developer.builder()
                .name("Bryan")
                .lastName("Cárdenas")
                .location("AYACUCHO, PERÚ")
                .web("https://portafolio-dev-rho.vercel.app/")
                .build();
        log.info(dev);
    }
}
```
