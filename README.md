# openoffice-lazybones
Lazybones project templates for Apache OpenOffice UNO projects.

## Using the Templates
See the [OpenOffice-Lazybones Documentation][1].

## Working on Templates
Project templates are found in the directories under the `templates` directory.

You can package and install all of your new or modified templates in a local Lazybones cache
 with the command:

    ./gradlew installAllTemplates

or a single template with

    ./gradlew installTemplate<TemplateName>

You'll then be able to use Lazybones to create new projects from these templates.
If you then want to distribute them, you will need to set up a Bintray account,
populate the `repositoryUrl`, `repositoryUsername` and `repositoryApiKey` settings
in `build.gradle`, add new Bintray packages in the repository via the Bintray
UI, and finally publish the templates with

    ./gradlew publishAllTemplates

or a single template with

    ./gradlew publishTemplate<TemplateName>

Alternatively you can manually upload and publish the templates with

    ./gradlew packageAllTemplates

or a single template with

    ./gradlew packageTemplate<TemplateName>

and find the packaged templates in `build/packages` ready for Bintray.

## Adding New Templates
To create a new template, simply create new directories under the `templates` directory
and put the source of the project template into it.

Edit the source of the template replacing class names and packages with variables 
to be expanded when the project is generated from the template.

Edit the lazybones.groovy file for other template actions.

You can find out more about creating templates on the [Lazybones GitHub wiki][2].

[1]: https://cbmarcum.github.io/openoffice-lazybones/
[2]: https://github.com/pledbrook/lazybones/wiki/Template-developers-guide
