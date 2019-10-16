# Project

## Tips

- Start early
- Start planning first, before you write any code
- Plan for extensibility

## Planning

- Given a set of customer arrival times in chrono order, output the discrete evts. ALso, output the stats at the end of the simulation

- in each level,
    - define a Main class with main method to handle input output
    - check for styling errors using checkstyle

- one server
    - serve one at a time
- when c arrives
    - if server `idle` => start serving (`served`)
    - if serving another => c `waits`
    - if serving another, another waitng => c `leaves`
    - when serve id one => served c leaves

 - level 4
    - evts are added
        - store in a list
    - iterate thru list
        - pick evt
            - schedule first evt
            
