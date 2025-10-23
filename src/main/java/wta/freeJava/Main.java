package wta.freeJava;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.jetbrains.annotations.ApiStatus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@ApiStatus.Internal
public class Main implements Plugin<Project> {
	@Override
	public void apply(Project project) {
		// Получаем sourceSets (обычно main и test)
		SourceSetContainer sourceSets = project.getExtensions().getByType(SourceSetContainer.class);
		SourceSet mainSourceSet = sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME);

		// Регистрируем задачу
		project.getTasks().register("compileJavaf", task -> {
			task.doLast(task2 -> {
				SourceDirectorySet javaSrcDirs = mainSourceSet.getJava();
				for (File srcDir : javaSrcDirs) {
					if (!srcDir.exists()) continue;
					try (Stream<Path> paths = Files.walk(srcDir.toPath())) {
						paths.filter(Files::isRegularFile)
							  .filter(p -> p.toString().endsWith(".javaf"))
							  .forEach(this::compileFile);
					} catch (IOException e) {
						throw new RuntimeException("FAILED to compile .javaf", e);
					}
				}
			});
		});

		// Зависимость: compile перед compileJava
		project.getTasks().named("compileJava").configure(t ->
			  t.dependsOn("compileJavaf")
		);
	}

	private void compileFile(Path javafPath) {
		Path javaPath = javafPath.resolveSibling(
			  javafPath.getFileName().toString().replace(".javaf", ".java")
		);

		try {
			String content = Files.readString(javafPath);
			String compiled = content.replace("const", "final");
			Files.writeString(javaPath, compiled);
		} catch (IOException e) {
			throw new RuntimeException("Error compiling file: " + javafPath, e);
		}
	}
}