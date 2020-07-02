#Run Test
1. Install JDK and Maven.
2. Install IntelliJ IDEA.
3. Launch IntelliJi and open ``parent-portal`` project.
4. From terminal window, install all dependencies by command: ``$ mvn clean install``.
5. Right click on file ``src/test/resources/runDemoTestcases.xml`` .
   and select Create Run Config by TestNG.
6. From terminal window, start running the test scripts by command: ``mvn clean test -DsuiteXmlFile=runDemoTestcases.xml``.
Or right click on `src/test/resources/runDemoTestcases.xml` and select Run .
-------------------

#Run in another browser
1. Open file ``src/test/resources/runDemoTestcases.xml``.
2. Update parameter browser value to: firefox, ie, edge.

-------------------

#Test eport
After running the test, estNG will create a folder to contain the report:
1. Open folder ``test-output/extendReport``.
2. Open file ``test-output/extendReport/DemoReport.html`` by browser.