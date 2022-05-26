const fs = require("fs");
const cpx = require("cpx");

const inputRootDir = "./docs";
const outputRootDir = "./public";

// Copy Images
const Images = (inputRootDir, outputRootDir) => {
  const inputDir = `${inputRootDir}/images`;
  const fileNameList = fs.readdirSync(inputDir);
  const docs = fileNameList.filter(RegExp.prototype.test, /.*\.*$/);

  const docsOutput = `${outputRootDir}/docs/images`;
  const specOutput = `${outputRootDir}/spec/docs/images`;
  docs.map((input) => {
    const source = `${inputDir}/**/*.*`;
    cpx.copy(source, docsOutput);
    cpx.copy(source, specOutput);
  });
};

Images(inputRootDir, outputRootDir);

// Copy Features
const Features = (inputRootDir, outputRootDir) => {
    const inputDir = `${inputRootDir}/cypress/integration`;
    const fileNameList = fs.readdirSync(inputDir);
    const docs = fileNameList.filter(RegExp.prototype.test, /.*\.feature$/);

    const docsOutput = `${outputRootDir}/cypress/integration`;
    const source = `${inputDir}/**/*.feature`;
    cpx.copy(source, docsOutput);
};

Features(".", "./docs");
Features(".", outputRootDir);

// Copy Jig
const Jig = (inputRootDir, outputRootDir) => {
    const inputDir = `${inputRootDir}/jig`;
    const fileNameList = fs.readdirSync(inputDir);
    const docsOutput = `${outputRootDir}/jig`;
    const source = `${inputDir}/**/*.*`;
    cpx.copy(source, docsOutput);
};

if (fs.existsSync("./build/jig")) {
    Jig("./build", outputRootDir);
}

// Copy schemaspy
const schemaspy = (inputRootDir, outputRootDir) => {
    const inputDir = `${inputRootDir}`;
    const docsOutput = `${outputRootDir}/images/schemaspy`;
    const source = `${inputDir}/output/**/diagrams/**/*.png`;
    cpx.copy(source, docsOutput);
};

const schemaspyHtml = (inputRootDir, outputRootDir) => {
    const inputDir = `${inputRootDir}`;
    const docsOutput = `${outputRootDir}/schemaspy`;
    const source = `${inputDir}/output/**/*.*`;
    cpx.copy(source, docsOutput);
};

if (fs.existsSync("./ops/build/docker/schemaspy/output")) {
    schemaspy("./ops/build/docker/schemaspy", "./docs");
    schemaspyHtml("./ops/build/docker/schemaspy", "./public");
}
