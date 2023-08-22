package Lab1

//import chiseltest._
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Counter Tester file" in {
    test(new Task2){a =>
    a.io.reload.expect(0.B)
    a.clock.step(10)
    //   b.io.out.expect(0.B)
    //   b.io.data_in.poke(5.U)

    }
  }
}
