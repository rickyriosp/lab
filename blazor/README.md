Templates are recognized by .NET because they have a special folder and config file at the root of your template folder.

First, create a new subfolder named .template.config, and enter it. Then, create a new file named template.json. Your folder structure should look like this:

JSON
```json
working
└───content
    └───consoleasync
        └───.template.config
                template.json
```

Open the template.json with your favorite text editor and paste in the following json code and save it.

```json
{
  "$schema": "http://json.schemastore.org/template",
  "author": "Me",
  "classifications": [ "Common", "Console" ],
  "identity": "ExampleTemplate.AsyncProject",
  "name": "Example templates: async project",
  "shortName": "consoleasync",
  "sourceName":"consoleasync",
  "tags": {
    "language": "C#",
    "type": "project"
  }
}
```

This config file contains all the settings for your template. You can see the basic settings, such as name and shortName, but there's also a tags/type value that's set to project. This categorizes your template as a "project" template. There's no restriction on the type of template you create. The item and project values are common names that .NET recommends so that users can easily filter the type of template they're searching for.

The sourceName item is what is replaced when the user uses the template. The value of sourceName in the config file is searched for in every file name and file content, and by default is replaced with the name of the current folder. When the -n or --name parameter is passed with the dotnet new command, the value provided is used instead of the current folder name. In the case of this template, consoleasync is replaced in the name of the .csproj file.

The classifications item represents the tags column you see when you run dotnet new and get a list of templates. Users can also search based on classification tags. Don't confuse the tags property in the template.json file with the classifications tags list. They're two different concepts that are unfortunately named the same. The full schema for the template.json file is found at the JSON Schema Store and is described at Reference for template.json. For more information about the template.json file, see the dotnet templating wiki.

Now that you have a valid .template.config/template.json file, your template is ready to be installed. Before you install the template, make sure that you delete any extra folders and files you don't want included in your template, like the bin or obj folders. In your terminal, navigate to the consoleasync folder and run dotnet new install .\ to install the template located at the current folder. If you're using a Linux or macOS operating system, use a forward slash: dotnet new install ./.

```bash
dotnet new install .\
```

This command outputs a list of the installed templates, which should include yours.

```console
The following template packages will be installed:
   <root path>\working\content\consoleasync

Success: <root path>\working\content\consoleasync installed the following templates:
Templates                                         Short Name               Language          Tags
--------------------------------------------      -------------------      ------------      ----------------------
Example templates: async project                  consoleasync             [C#]              Common/Console
```
