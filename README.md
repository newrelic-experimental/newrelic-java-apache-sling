<a href="https://opensource.newrelic.com/oss-category/#new-relic-experimental"><picture><source media="(prefers-color-scheme: dark)" srcset="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/dark/Experimental.png"><source media="(prefers-color-scheme: light)" srcset="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/Experimental.png"><img alt="New Relic Open Source experimental project banner." src="https://github.com/newrelic/opensource-website/raw/main/src/images/categories/Experimental.png"></picture></a>

![GitHub forks](https://img.shields.io/github/forks/newrelic-experimental/newrelic-java-apache-sling?style=social)
![GitHub stars](https://img.shields.io/github/stars/newrelic-experimental/newrelic-java-apache-sling?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/newrelic-experimental/newrelic-java-apache-sling?style=social)

![GitHub all releases](https://img.shields.io/github/downloads/newrelic-experimental/newrelic-java-apache-sling/total)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/newrelic-experimental/newrelic-java-apache-sling)
![GitHub last commit](https://img.shields.io/github/last-commit/newrelic-experimental/newrelic-java-apache-sling)
![GitHub Release Date](https://img.shields.io/github/release-date/newrelic-experimental/newrelic-java-apache-sling)


![GitHub issues](https://img.shields.io/github/issues/newrelic-experimental/newrelic-java-apache-sling)
![GitHub issues closed](https://img.shields.io/github/issues-closed/newrelic-experimental/newrelic-java-apache-sling)
![GitHub pull requests](https://img.shields.io/github/issues-pr/newrelic-experimental/newrelic-java-apache-sling)
![GitHub pull requests closed](https://img.shields.io/github/issues-pr-closed/newrelic-experimental/newrelic-java-apache-sling)

# New Relic Java Instrumentation for Apache Sling

Provides instrumentation for the Apache Sling framework.



## Installation

This use this instrumentation.   
1. Download the latest release.    
2. In the New Relic Java Agent directory (directory containing newrelic.jar), create a directory named extensions if it doe not already exist.   
3. Copy the jars into the extensions directory.
4. Add a property to newrelic.yml file indicating the list of resources extensions that must be tracked as transactions. Those extensions not specified here are bunched together as one transaction (for example, all .js files will appear under **/*.js). 

 sling.naming.labs.extensions: html, json, jsp

This jar instruments the org.apache.sling.engine.impl.SlingMainServlet servlet and renames transaction by first extracting the resource URL. It examines the file extension and if that extension matches one specified in the list above, it then extracts the first part of the URL (the part before the first “/” and appends the file name to that with “...” in between them) to create the transaction name.

5. Restart the application.


## Getting Started

After deployment, you should be able to see parts of your Apache Sling flows showing up in transaction traces.

## Building

If you make changes to the instrumentation code and need to build the instrumentation jars, follow these steps
1. Set environment variable NEW_RELIC_EXTENSIONS_DIR.  Its value should be the directory where you want to build the jars (i.e. the extensions directory of the Java Agent).   
2. Build one or all of the jars.   
a. To build one jar, run the command:  gradlew _moduleName_:clean  _moduleName_:install    
b. To build all jars, run the command: gradlew clean install
3. Restart the application

## Support

New Relic has open-sourced this project. This project is provided AS-IS WITHOUT WARRANTY OR DEDICATED SUPPORT. Issues and contributions should be reported to the project here on GitHub.

>[Choose 1 of the 2 options below for Support details, and remove the other one.]

>[Option 1 - no specific thread in Community]
>We encourage you to bring your experiences and questions to the [Explorers Hub](https://discuss.newrelic.com) where our community members collaborate on solutions and new ideas.

>[Option 2 - thread in Community]
>New Relic hosts and moderates an online forum where customers can interact with New Relic employees as well as other customers to get help and share best practices. Like all official New Relic open source projects, there's a related Community topic in the New Relic Explorers Hub.
>You can find this project's topic/threads here: [URL for Community thread]

## Contributing

We encourage your contributions to improve [Project Name]! Keep in mind when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project. If you have any questions, or to execute our corporate CLA, required if your contribution is on behalf of a company, please drop us an email at opensource@newrelic.com.

**A note about vulnerabilities**

As noted in our [security policy](../../security/policy), New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through [HackerOne](https://hackerone.com/newrelic).

## License

[Project Name] is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.

>[If applicable: [Project Name] also uses source code from third-party libraries. You can find full details on which libraries are used and the terms under which they are licensed in the third-party notices document.]
