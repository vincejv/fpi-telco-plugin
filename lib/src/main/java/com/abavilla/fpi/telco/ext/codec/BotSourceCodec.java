package com.abavilla.fpi.telco.ext.codec;

import com.abavilla.fpi.fw.config.codec.AbsEnumCodec;
import com.abavilla.fpi.telco.ext.enums.BotSource;

/**
 * MongoDB Codec for {@link BotSource} enum.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public class BotSourceCodec extends AbsEnumCodec<BotSource> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<BotSource> getEncoderClass() {
    return BotSource.class;
  }
}
