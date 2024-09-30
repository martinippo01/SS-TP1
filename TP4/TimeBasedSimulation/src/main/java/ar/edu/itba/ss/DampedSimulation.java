package ar.edu.itba.ss;

import java.util.List;
import java.util.function.BiConsumer;

public class DampedSimulation extends Simulation {
    private final double gamma;

    public DampedSimulation(double k, double A, double timeMax, double dt, double mass, AlgorithmType algorithmType, BiConsumer<List<Particle>, Long> onStep, double gamma) {
        super(k, A, timeMax, dt, mass, algorithmType, onStep);
        this.gamma = gamma;
    }

    @Override
    public Algorithm getAlgorithm() {
        double k = this.getK();
        double A = this.getA();
        double mass = this.getMass();
        double r0 = A;
        double v0 = -A * gamma / (2 * mass);
        Particle p = new Particle(new Position(0,r0), new Velocity(0, v0), mass);
        double dt = this.getDt();

        AlgorithmType algorithmType = this.getAlgorithmType();
        return algorithmType.getAlgorithm(k, gamma, p, dt);
    }
}
