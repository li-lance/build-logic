import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class SprintBootConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // 应用 Spring Boot 插件
            apply(plugin = "org.springframework.boot")
//            apply(plugin = "io.spring.dependency-management")

            // 配置 Spring Boot
//            configureSpringBoot()
        }
    }
}