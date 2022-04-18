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
    const source = `${inputDir}/${input}`;
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
  docs.map((input) => {
    const source = `${inputDir}/${input}`;
    cpx.copy(source, docsOutput);
  });
};

Features(".", "./docs");
