package github.iharuya.japanizer;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ApplicationPlugin;

@NonNullApi
abstract public class MyJavaApplicationPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(MyJavaBasePlugin.class);
        project.getPlugins().apply(ApplicationPlugin.class);
    }
}