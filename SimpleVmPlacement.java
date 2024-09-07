package com.bhuvana.VmPlacement;

import org.cloudbus.cloudsim.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.core.SimEntity;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class SimpleVmPlacement {

    public static void main(String[] args) {
        // Initialize CloudSim
        CloudSim simulation = new CloudSim();
        
        // Create Datacenter
        List<Host> hostList = createHosts();
        Datacenter datacenter = new DatacenterSimple(simulation, hostList);

        // Create Datacenter Broker
        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        // Create VMs
        List<Vm> vmList = createVms();
        broker.submitVmList(vmList);

        // Start the simulation
        simulation.start();
    }

    private static List<Host> createHosts() {
        List<Host> hostList = new ArrayList<>();
        int mips = 1000;
        int ram = 2048;
        long storage = 10000;
        long bw = 10000;
        int pesNumber = 4;

        for (int i = 0; i < 2; i++) {
            List<Pe> peList = new ArrayList<>();
            for (int j = 0; j < pesNumber; j++) {
                peList.add(new PeSimple(mips));
            }
            Host host = new HostSimple(ram, bw, storage, peList);
            hostList.add(host);
        }
        return hostList;
    }

    private static List<Vm> createVms() {
        List<Vm> vmList = new ArrayList<>();
        int mips = 1000;
        int pesNumber = 2;
        int ram = 512;
        long bw = 1000;
        long size = 1000;

        for (int i = 0; i < 5; i++) {
            Vm vm = new VmSimple(mips, pesNumber)
                    .setRam(ram)
                    .setBw(bw)
                    .setSize(size);
            vmList.add(vm);
        }
        return vmList;
    }
}

