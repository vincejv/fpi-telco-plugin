/******************************************************************************
 * FPI Application - Abavilla                                                 *
 * Copyright (C) 2022  Vince Jerald Villamora                                 *
 *                                                                            *
 * This program is free software: you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, either version 3 of the License, or          *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.     *
 ******************************************************************************/

package com.abavilla.fpi.telco.ext.entity.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.abavilla.fpi.fw.entity.enums.IBaseEnum;
import com.abavilla.fpi.fw.util.FWConst;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Enum for storing the API Status from Rest call or response
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@AllArgsConstructor
@RegisterForReflection
public enum ApiStatus implements IBaseEnum {
  DEL(1, 70000L, "SUCCESS", "Delivered"),
  UND(2, null,null,"Undelivered"),
  ACK(8, null, null, "Acknowledged"),
  INV(15, 90000L, null, "Invalid"),
  REJ(16, 90200L, "FAILED","Rejected"),
  CREATED(200, null, null, "Created"),
  DUPL(300, 90380L, null, "Duplicated"),
  UNKNOWN(-1, null, null, FWConst.UNKNOWN_PREFIX),
  WAIT(-2, null, null, "Waiting");

  /**
   * Ordinal id to enum mapping
   */
  private static final Map<Integer, IBaseEnum> ENUM_MAP = new HashMap<>();

  static {
    for(IBaseEnum w : EnumSet.allOf(ApiStatus.class))
      ENUM_MAP.put(w.getId(), w);
  }

  /**
   * The enum ordinal id
   */
  private final int id;

  /**
   * The DTOne status code
   */
  private final Long dtOneId;

  /**
   * The GlobeLabs response message
   */
  private final String glResp;

  /**
   * The enum value
   */
  private final String value;

  /**
   * Creates an enum based from given string value
   *
   * @param value the string value
   * @return the created enum
   */
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static ApiStatus fromValue(String value) {
    return (ApiStatus) IBaseEnum.fromValue(value, ENUM_MAP, UNKNOWN);
  }

  /**
   * Creates an enum based from given an ordinal id
   *
   * @param id the ordinal id
   * @return the created enum
   */
  public static ApiStatus fromId(int id) {
    return (ApiStatus) IBaseEnum.fromId(id, ENUM_MAP, UNKNOWN);
  }

  /**
   * Creates an enum based from given a DTOne DVS status code
   *
   * @param dtStsId the status code
   * @return the created enum
   */
  public static ApiStatus fromDtOne(Long dtStsId) {
    return (ApiStatus) ENUM_MAP.values().stream()
      .filter(enumItem -> Objects.equals(dtStsId, ((ApiStatus)enumItem).dtOneId))
      .findAny().orElse(UNKNOWN);
  }

  /**
   * Creates an enum based from given GlobeLabs status message
   *
   * @param glSts the status message
   * @return the created enum
   */
  public static ApiStatus fromGL(String glSts) {
    return (ApiStatus) ENUM_MAP.values().stream()
      .filter(enumItem -> StringUtils.equals(glSts, ((ApiStatus)enumItem).glResp)).findAny()
      .orElse(UNKNOWN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @JsonValue
  public String toString() {
    return value;
  }

}
