Creating your own custom PMD rulesets

Creating new rulesets .xml file

Each PMD ruleset is defined in a single XML file. Creating a new ruleset file is very simple. Just copy the code below into a new XML file, change the name, description and you are ready to add your rulesets.

<?xml version="1.0" encoding="UTF-8"?>
<ruleset name="Custom ruleset"
xmlns="http://pmd.sf.net/ruleset/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://pmd.sf.net/ruleset/1.0.0 http://pmd.sf.net/ruleset_xml_schema.xsd"
xsi:noNamespaceSchemaLocation="http://pmd.sf.net/ruleset_xml_schema.xsd">
<description>This ruleset detects bad practices in code my code</description>

<!-- Place to add your rules -->
</ruleset>
Adding new rules

There are 2 ways that you can add particular set of rules:

1. You can either specify each rule that you wish to add

<!-- Braces Pmd rules: -->
<rule ref="rulesets/java/braces.xml/ForLoopsMustUseBraces"/>
<rule ref="rulesets/java/braces.xml/IfElseStmtsMustUseBraces"/>
<rule ref="rulesets/java/braces.xml/IfStmtsMustUseBraces"/>
<rule ref="rulesets/java/braces.xml/WhileLoopsMustUseBraces"/>
2. Or add full set of rules at once

<!-- Braces Pmd rules: -->
<rule ref="rulesets/java/braces.xml"/>
For full list of all available rulesets please visit the PMD website.

Excluding rules from a ruleset

Some of the rulesets contain many rules that you may not want to use. Therefore you can exclude some of the rules and here is how to do it:

<!-- Braces Pmd rules: -->
<rule ref="rulesets/java/braces.xml"/>
<exclude name="IfElseStmtsMustUseBraces"/>
</rule>
Excluding files from rulesets

Here you can either exclude class or whole package from rulesets, which gives you more control over your application.

1. Excluding class

<!-- Check for Empty Catch blocks -->
<rule ref="rulesets/java/empty.xml/EmptyCatchBlock"/>
<exclude-pattern>.*/src/org/jpedal/constants/JPedalSettings.*</exclude-pattern>
2. Excluding package

<!-- Check for Empty Catch blocks -->
<rule ref="rulesets/java/empty.xml/EmptyCatchBlock"/>
<exclude-pattern>.*/src/org/jpedal/constants/.*</exclude-pattern>
Additionally you can exclude particular rule violation in your own code classes using @SuppressWarnings such as:

@SuppressWarnings("PMD")
private static void saveAsJPEG(final String jpgFlag) throws IOException {

}
Adding comments

To add comments to your xml file you can either add <description> or <!– –>. Notice that <!– –> will allow you comment out a blocks of tags.

<description>Basic Pmd rules:</description>

<!-- Basic Pmd rules: -->