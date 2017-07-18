package cn.yesway.pms.generator.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yesway.pms.generator.dao.BusinessCodeGeneratorDao;
import cn.yesway.pms.generator.entity.GeneratorConfig;

@Service("generator")
public class BusinessCodeGenerator extends AbstractBusinessCodeGenerator {

	@Autowired
	private BusinessCodeGeneratorDao businessCodeGeneratorDao;

	@Override
	public GeneratorConfig initConfig(String seed) {
		GeneratorConfig generatorConfig = businessCodeGeneratorDao.findBySeed(seed);
		changeConfigByChange(generatorConfig);
		return generatorConfig;
	}

	@Override
	public String getPrefix() {
		return generatorConfig.getPrefix();
	}

	@Override
	public String getTimestamp() {
		return formatterTimestamp();
	}

	@Override
	public String getNumber() {
		Integer number = generatorConfig.getNumber();
		Integer digit = generatorConfig.getDigit();
		if (number.toString().length() > digit) {
			throw new IndexOutOfBoundsException("number is fulled");
		}
		String n = String.format("%06d", number);
		number += 1;
		generatorConfig.setNumber(number);
		return n;
	}

	@Override
	public void flush() {
		businessCodeGeneratorDao.update(generatorConfig);
	}

	private String formatterTimestamp() {
		String formatter = generatorConfig.getFormatter();
		if (formatter.isEmpty()) {
			throw new NullPointerException("formatter is null");
		}
		StringBuilder timestamp = new StringBuilder();
		LocalDate date = generatorConfig.getDate();
		if ("yyyy".equals(formatter)) {
			timestamp.append(date.getYear());
		} else if ("yyyyMM".equals(formatter)) {
			timestamp.append(date.getYear());
			timestamp.append(String.format("%02d", date.getMonthValue()));
		} else if ("yyyyMMdd".equals(formatter)) {
			timestamp.append(date.getYear());
			timestamp.append(String.format("%02d", date.getMonthValue()));
			timestamp.append(String.format("%02d", date.getDayOfMonth()));
		} else {
			throw new RuntimeException("formatter unidentifiable");
		}
		return timestamp.toString();
	}

	private void changeConfigByChange(GeneratorConfig generatorConfig) {
		if (isChanged(generatorConfig)) {
			generatorConfig.setDate(LocalDate.now());
			generatorConfig.setNumber(1);
		}
	}

	private Boolean isChanged(GeneratorConfig generatorConfig) {
		LocalDate date = LocalDate.now();
		LocalDate confDate = generatorConfig.getDate();
		Boolean changed = false;
		switch (generatorConfig.getFormatter()) {
		case "yyyy":
			if (date.getYear() != confDate.getYear()) {
				changed = true;
			}
			break;
		case "yyyyMM":
			if (date.getYear() != confDate.getYear() || date.getMonthValue() != confDate.getMonthValue()) {
				changed = true;
			}
			break;
		case "yyyyMMdd":
			if (!date.equals(confDate)) {
				changed = true;
			}
			break;
		default:
			throw new RuntimeException("formatter unidentifiable");
		}
		return changed;
	}

}
