const fs = require("fs");

fs.readdir("cypress/cucumber-json/", function (err, files) {
  if (err) throw err;

  const result = [];
  files.forEach((file) => {
    const jsonObject = JSON.parse(
      fs.readFileSync(`cypress/cucumber-json/${file}`, "utf8")
    );
    result.push(jsonObject[0]);
  });

  if (!fs.existsSync("public")) {
    fs.mkdirSync("public", (err, folder) => {
      if (err) throw err;
      console.log(folder);
    });
  }

  if (!fs.existsSync("public/report")) {
    fs.mkdirSync("public/report", (err, folder) => {
      if (err) throw err;
      console.log(folder);
    });
  }

  fs.writeFileSync("public/report/cucumber.json", JSON.stringify(result));
});
