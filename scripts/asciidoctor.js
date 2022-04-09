const asciidoctor = require("@asciidoctor/core")();
const kroki = require("asciidoctor-kroki");

const krokiRegister = () => {
  const registry = asciidoctor.Extensions.create();
  kroki.register(registry);
  return registry;
};

const inputRootDir = "./docs";
const outputRootDir = "./public/docs";

const dosc = ["index.adoc", "sample.adoc"];
dosc.map((input) => {
  const file = `${inputRootDir}/${input}`;
  asciidoctor.convertFile(file, {
    safe: "safe",
    extension_registry: krokiRegister(),
    to_dir: outputRootDir,
    mkdirs: true,
  });
});
