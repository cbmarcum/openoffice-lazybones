import uk.co.cacoethes.util.NameType
import org.apache.commons.io.FileUtils

Map props = [:]
if (projectDir.name =~ /\-/) {
    props.project_class_name = transformText(projectDir.name, from: NameType.HYPHENATED, to: NameType.CAMEL_CASE)
} else {
    props.project_class_name = transformText(projectDir.name, from: NameType.PROPERTY, to: NameType.CAMEL_CASE)
}
props.project_name = transformText(props.project_class_name, from: NameType.CAMEL_CASE, to: NameType.HYPHENATED)

props.project_group = ask("Define value for 'group' [org.example]: ", "org.example", "group")
props.project_name = ask("Define value for 'artifactId' [" + props.project_name + "]: ", props.project_name , "artifactId")
props.project_version = ask("Define value for 'version' [0.1.0]: ", "0.1.0", "version")
props.project_package = ask("Define value for 'package' [" + props.project_group + "]: ", props.project_group, "package")
props.project_class_name = ask("Define value for 'className' [" + props.project_class_name + "]: ", props.project_class_name, "className").capitalize()
props.project_property_name = transformText(props.project_class_name, from: NameType.CAMEL_CASE, to: NameType.PROPERTY)
props.project_capitalized_name = props.project_property_name.capitalize()
props.project_lowercase_name = props.project_property_name.toString().toLowerCase()

props.package_path = props.project_package.replace('.' as char, '/' as char)
props.module_path = props.project_package.replace('.' as char, '_' as char)

// strings used in IDL file (if we had one)
props.module_start = ""
props.module_end = ""
props.project_package.split('\\.').each {
     props.module_start += "module ${it} { "
     props.module_end += "}; "
}
props.module_start = props.module_start.trim()
props.module_end = props.module_end.trim()
props.build = '$build'
props.office_home_dir='C\\:\\\\Program Files (x86)\\\\OpenOffice 4'
props.office_tool_path='C\\:\\\\Program Files (x86)\\\\OpenOffice 4\\\\program'
props.dollar='$'

props.buildDir = '$buildDir'
// props.prefix = '$prefix'
// props.it = 'it'

// used in this script
String packagePath = props.package_path

processTemplates 'build.gradle', props
processTemplates 'settings.gradle', props
processTemplates 'gradle.properties', props
processTemplates 'README.md', props
processTemplates 'ant/ant.properties', props
processTemplates 'ant/build.xml', props
processTemplates 'src/main/dist/description/description_en.txt', props
processTemplates 'src/main/dist/META-INF/manifest.xml', props
processTemplates 'src/main/dist/registry/data/org/openoffice/Office/Addons.xcu', props
processTemplates 'src/main/dist/registry/data/org/openoffice/Office/ProtocolHandler.xcu', props
processTemplates 'src/main/dist/description.xml', props
processTemplates 'src/main/groovy/*.groovy', props
processTemplates 'src/main/groovy/*.java', props
processTemplates 'src/main/groovy/*.idl', props
processTemplates 'src/test/groovy/*.groovy', props

File mainSources = new File(projectDir, 'src/main/groovy')
File testSources = new File(projectDir, 'src/test/groovy')

File mainSourcesPath = new File(mainSources, packagePath)
mainSourcesPath.mkdirs()
File testSourcesPath = new File(testSources, packagePath)
testSourcesPath.mkdirs()

def renameFile = { File from, String path ->
    if (from.file) {
        File to = new File(path)
        to.parentFile.mkdirs()
        FileUtils.moveFile(from, to)
    }
}

mainSources.eachFile { File file ->
    renameFile(file, mainSourcesPath.absolutePath + '/' + file.name)
}
testSources.eachFile { File file ->
    renameFile(file, testSourcesPath.absolutePath + '/' + props.project_capitalized_name + file.name)
}

renameFile(new File(mainSourcesPath, 'GroovyAddOn.groovy'), mainSourcesPath.absolutePath + '/' + props.project_class_name + ".groovy")
// renameFile(new File(mainSourcesPath, 'GroovyAddIn.idl'), mainSourcesPath.absolutePath + '/' + props.project_class_name + ".idl")
// renameFile(new File(mainSourcesPath, 'XGroovyAddIn.idl'), mainSourcesPath.absolutePath + '/X' + props.project_class_name + ".idl")
