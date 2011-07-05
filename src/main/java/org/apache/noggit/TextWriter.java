/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.noggit;

import java.util.*;

/**
 * @author yonik
 * @version $Id: TextWriter.java 1069578 2011-02-10 21:41:43Z yonik $
 */
public abstract class TextWriter {
  public abstract void writeNull();

  public abstract void writeString(CharSequence str);

  public abstract void writeString(CharArr str);

  public abstract void writeStringStart();
  public abstract void writeStringChars(CharArr partialStr);
  public abstract void writeStringEnd();

  public abstract void write(long number);
  public abstract void write(int number);
  public void write(short number) { write ((int)number); }
  public void write(byte number) { write((int)number); }

  public abstract void write(double number);
  public abstract void write(float number);

  public abstract void write(boolean bool);


  /** A char[] may be either be a string, or a list of characters.
   * It's up to the implementation to decide.
   */
  public abstract void write(char[] val);

  public abstract void writeNumber(CharArr digits);
  
  public abstract void writePartialNumber(CharArr digits);

  public abstract void startObject();

  public abstract void endObject();

  public abstract void startArray();

  public abstract void endArray();

  public abstract void writeValueSeparator();

  public abstract void writeNameSeparator();

  public void write(Object o) {
    if (o == null) {
      writeNull();
    } else if (o instanceof CharSequence) {
      writeString((CharSequence)o);
    } else if (o instanceof Number) {
      if (o instanceof Integer || o instanceof Long) {
        write(((Number)o).longValue());
      } else if (o instanceof Float || o instanceof Double) {
        write(((Number)o).doubleValue());
      } else {
        CharArr arr = new CharArr();
        arr.write(o.toString());
        writeNumber(arr);
      }
    } else if (o instanceof Map) {
      write((Map)o);
    } else if (o instanceof Collection) {
      write((Collection)o);
    } else if (o instanceof Object[]) {
      write(Arrays.asList((Object[])o));
    } else if (o instanceof Boolean) {
      write(((Boolean)o).booleanValue());
    } else if (o instanceof int[]) {
      write((int[])o);
    } else if (o instanceof float[]) {
      write((float[])o);
    } else if (o instanceof long[]) {
      write((long[])o);
    } else if (o instanceof double[]) {
      write((double[])o);
    } else if (o instanceof short[]) {
      write((short[])o);
    } else if (o instanceof boolean[]) {
      write((boolean[])o);
    } else if (o instanceof char[]) {
      write((char[])o);
    } else if (o instanceof byte[]) {
      write((byte[])o);
    } else {
      writeString(o.toString());
    }
  }

  public void write(Map val) {
    startObject();
    boolean first = true;
    for (Map.Entry entry : (Set<Map.Entry>)val.entrySet()) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      writeString(entry.getKey().toString());
      writeNameSeparator();
      write(entry.getValue());
    }
    endObject();
  }

  public void write(Collection val) {
    startArray();
    boolean first = true;
    for (Object o : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(o);
    }
    endArray();
  }

  /** A byte[] may be either a single logical value, or a list of small integers.
   * It's up to the implementation to decide.
   */
  public void write(byte[] val) {
    startArray();
    boolean first = true;
    for (short v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(short[] val) {
    startArray();
    boolean first = true;
    for (short v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(int[] val) {
    startArray();
    boolean first = true;
    for (int v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(long[] val) {
    startArray();
    boolean first = true;
    for (long v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(float[] val) {
    startArray();
    boolean first = true;
    for (float v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(double[] val) {
    startArray();
    boolean first = true;
    for (double v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

  public void write(boolean[] val) {
    startArray();
    boolean first = true;
    for (boolean v : val) {
      if (first) {
        first = false;
      } else {
        writeValueSeparator();
      }
      write(v);
    }
    endArray();
  }

}

