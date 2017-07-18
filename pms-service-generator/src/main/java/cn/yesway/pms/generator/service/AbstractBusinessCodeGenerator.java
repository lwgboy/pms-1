package cn.yesway.pms.generator.service;

import cn.yesway.pms.generator.entity.GeneratorConfig;

public abstract class AbstractBusinessCodeGenerator implements Generator {

	protected GeneratorConfig generatorConfig;

	@Override
	public synchronized String generate(String seed) {
		this.generatorConfig = initConfig(seed);
		if (this.generatorConfig == null) {
			throw new NullPointerException("generatorConfig is null");
		}
		StringBuilder result = new StringBuilder();
		result.append(getPrefix());
		result.append(getTimestamp());
		result.append(getNumber());
		flush();
		return result.toString();
	}

	protected abstract GeneratorConfig initConfig(String seed);

	protected abstract String getPrefix();

	protected abstract String getTimestamp();

	protected abstract String getNumber();

	protected abstract void flush();
}
