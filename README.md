# Flink Labs


## Create project tempalte

```
mvn archetype:generate                            \
  -DarchetypeGroupId=org.apache.flink              \
  -DarchetypeArtifactId=flink-quickstart-java      \
  -DarchetypeVersion=1.13.0                        \
  -DgroupId=com.example                            \
  -DartifactId=my-flink-project                    \
  -Dversion=0.1                                    \
  -Dpackage=com.example.flink
```


## Local Develop

- Add local runtime

```
<dependency>
    <groupId>org.apache.flink</groupId>
    <artifactId>flink-runtime-web_${scala.binary.version}</artifactId>
    <version>${flink.version}</version>
</dependency>
```

- Change scope from `provided` to `compile` of dependencies:
  - `flink-streaming-java`
  - `flink-clients`
  - `flink-java`

