## component-id-generator

### use cases
manual cli
```bash
java -jar component-id-generator.jar input.json output.json
```

automation with gradle
```kts
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.jamilservices:component-id-generator:1.0.1")
    }
}
tasks.register("jcomponentIdGenerator") {
    group ="jamilservices"
    dependsOn(tasks.setupDependencies)
    JComponentIdGenerator.main(arrayOf("src/main/resources/components/component_names.json", "src/main/resources/components/component_ids.json"))
}
tasks.register<Copy>("copyIdsGeneratedResources") {
    group ="jamilservices"
    dependsOn("jcomponentIdGenerator")
    from("src/main/resources/components/component_*.json")
    into("build/resources/components")
}
tasks.named("processResources") {
    dependsOn("copyIdsGeneratedResources")
}
```

### Input Json Example
```json
[
  "prefix.component1",
  "prefix.component2",
  "prefix.component3"
]
```

### Output Json Example
```json
{
  "prefix.component1": "06737df5-5af9-439e-ba98-cbad6c692519",
  "prefix.component2": "846c9b3c-99a6-4662-a966-d404fc0380c9",
  "prefix.component3": "5d4ab4b9-58ad-4b0f-9db1-2307ca9c9841"
}
```

#### Public GPG on [keyserver.ubuntu.com](https://keyserver.ubuntu.com/pks/lookup?fingerprint=on&op=index&search=0xAD7CC3F39E5EA79376ABA336F7D1CE71D667E0E6)
#### Maven Central Repository [https://central.sonatype.com/artifact/com.jamilservices/component-id-generator](https://central.sonatype.com/artifact/com.jamilservices/component-id-generator/overview)