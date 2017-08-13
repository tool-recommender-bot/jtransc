/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package java.time.temporal;

public interface TemporalAccessor {
	boolean isSupported(TemporalField field);

	long getLong(TemporalField field);

	default ValueRange range(TemporalField field) {
		if (field instanceof ChronoField) {
			return field.range();
		} else {
			return field.rangeRefinedBy(this);
		}
	}

	default int get(TemporalField field) {
		ValueRange range = range(field);
		long value = getLong(field);
		return (int) value;
	}

	default <R> R query(TemporalQuery<R> query) {
		return query.queryFrom(this);
	}

}
