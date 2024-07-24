package com.betrybe.minhaconta.business;

import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;


/**
 * The type Energy account.
 */
public class EnergyAccount {

  Client client;

  public EnergyAccount(Client client) {
    this.client = client;
  }

  /**
   * Req. 11 – Find high consumption device per address.
   */
  public ElectronicDevice[] findHighConsumptionDevices() {
    Address[] addresses = client.getAddressesAsArray();
    ElectronicDevice[] result = new ElectronicDevice[addresses.length];
    for (int i = 0; i < addresses.length; i++) {
      ElectronicDevice[] electronicDevices = addresses[i].getDevicesAsArray();
      for (ElectronicDevice electronicDevice : electronicDevices) {
        if (result[i] == null) {
          result[i] = electronicDevice;
        } else {
          double kwhResult = result[i].monthlyKwh();
          double kwhAtual = electronicDevice.monthlyKwh();
          if (kwhAtual > kwhResult) {
            result[i] = electronicDevice;
          }
        }
      }
    }
    return result;
  }
}
