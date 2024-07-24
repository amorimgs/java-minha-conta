package com.betrybe.minhaconta.presentation;

import com.betrybe.minhaconta.business.EnergyAccount;
import com.betrybe.minhaconta.business.EnergyBill;
import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;
import com.ions.lightdealer.sdk.service.LightDealerApi;

/**
 * The type Application.
 */
public class Application {

  ConsoleUserInterface ui;
  LightDealerApi api;

  /**
   * Constructor that instantiates a new Application.
   */
  public Application(ConsoleUserInterface ui) {
    this.ui = ui;
    this.api = new LightDealerApi();
  }

  /**
   * Req. 4 – Creates CLI menu.
   */
  public void run() {

    String[] options = {"1 - Cadastrar cliente",
        "2 - Cadastrar imóvel de cliente",
        "3 - Cadastrar dispositivos em imóvel",
        "4 - Estimar conta de imóvel",
        "5 - Otimizar uso de energia",
        "6 - Sair"};

    char option = ui.inputMenuOption(options);
    runOptionAction(option);
  }

  /**
   * Req. 5 – Run menu options.
   */
  public void runOptionAction(char option) {
    switch (option) {
      case '1':
        registerClient();
        break;
      case '2':
        registerClientAddress();
        break;
      case '3':
        registerAddressDevices();
        break;
      case '4':
        estimateAddressBill();
        break;
      case '5':
        optimizeEnergyBill();
        break;
      case '6':
        ui.showMessage("Volte sempre!");
        break;
      default:
        ui.showMessage("Opção inválida!");
        break;
    }
  }

  /**
   * Req. 6 – Register client.
   */
  public void registerClient() {
    Client client = new Client();
    ui.fillClientData(client);
    api.addClient(client);
  }

  /**
   * Req. 7 – Register client address.
   */
  public void registerClientAddress() {
    Client client = api.findClient(ui.inputClientCpf());
    if (client == null) {
      ui.showMessage("Pessoa cliente não encontrada!");
    } else {
      Address address = new Address();
      ui.fillAddressData(address);
      api.addAddressToClient(address, client);
    }
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
    Address address = api.findAddress(ui.inputAddressRegistration());
    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      int numberDevice = ui.inputNumberOfDevices();

      for (int i = 1; i <= numberDevice; i++) {
        ElectronicDevice device = new ElectronicDevice();
        ui.fillDeviceData(device);

        api.addDeviceToAddress(device, address);
      }
    }
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
    Address address = api.findAddress(ui.inputAddressRegistration());
    if (address == null) {
      ui.showMessage("Endereço não encontrado!");
    } else {
      EnergyBill energyBill = new EnergyBill(address, true);
      double valor = energyBill.estimate();
      ui.showMessage("Valor estimado para a conta: " + valor);
    }
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
  }
}
