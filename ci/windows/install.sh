#!/bin/bash

# ci/windows/install.sh

set -ev

# FeatureMain	Core AdoptOpenJDK installation (DEFAULT)
# FeatureEnvironment	Update the PATH environment variable (DEFAULT)
# FeatureJarFileRunWith	Associate .jar files with Java applications (DEFAULT)
# FeatureJavaHome	Update the JAVA_HOME environment variable
# FeatureIcedTeaWeb	Install IcedTea-Web
# FeatureJNLPFileRunWith	Associate .jnlp files with IcedTea-web
# FeatureOracleJavaSoft	Updates registry keys HKLM/SOFTWARE/JavaSoft/
choco install adoptopenjdk --version 13.102.8 -y -ia ADDLOCAL=FeatureMain,FeatureEnvironment,FeatureJavaHome
echo 'export JAVA_HOME="/c/Program Files/AdoptOpenJDK/jdk-13.0.2.8-hotspot"' >> .travis.env
echo 'export PATH="$JAVA_HOME/bin":$PATH' >> .travis.env
echo 'export GRADLE_OPTS="-Dorg.gradle.daemon=false"' >> .travis.env
