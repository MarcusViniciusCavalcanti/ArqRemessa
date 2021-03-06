package br.com.nordestefomento.jrimum.texgit.engine;

import static br.com.nordestefomento.jrimum.utilix.ObjectUtil.isNotNull;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.nordestefomento.jrimum.texgit.language.MetaRecord;
import br.com.nordestefomento.jrimum.texgit.type.component.RecordFactory;
import br.com.nordestefomento.jrimum.texgit.type.component.Record;


public class Factory4Record implements RecordFactory<Record> {

	private Map<String, MetaRecord> name_record;

	Factory4Record(List<MetaRecord> metaRecords) {

		if (isNotNull(metaRecords)) {
			if (!metaRecords.isEmpty()) {

				name_record = new HashMap<String, MetaRecord>(metaRecords
						.size());

				for (MetaRecord mRecord : metaRecords) {

					name_record.put(mRecord.getName(), mRecord);

					if (isNotNull(mRecord.getGroupOfInnerRecords()))
						loadInnerRecords(name_record, mRecord
								.getGroupOfInnerRecords().getRecords());
				}
			}
		}
	}

	private void loadInnerRecords(Map<String, MetaRecord> name_record,
			List<MetaRecord> innerMetaRecords) {

		if (isNotNull(innerMetaRecords)) {
			if (!innerMetaRecords.isEmpty()) {

				for (MetaRecord iMetaRecord : innerMetaRecords) {

					name_record.put(iMetaRecord.getName(), iMetaRecord);

					if (isNotNull(iMetaRecord.getGroupOfInnerRecords()))
						loadInnerRecords(name_record, iMetaRecord
								.getGroupOfInnerRecords().getRecords());
				}
			}
		}

	}

	@Override
	public Record create(String name) {

		Record record = null;

		if (isNotBlank(name))
			if (name_record.containsKey(name))
				record = Builder4Record.build(name_record.get(name));

		return record;
	}
}
