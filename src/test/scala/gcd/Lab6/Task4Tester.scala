package Lab6



//import chiseltest._
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task4Tester extends FreeSpec with ChiselScalatestTester {
  "lab 6 task 4 Tester file" in {
    test(new up_down_counter){a =>
        a.io.up_down.poke(0.B)
        a.clock.step(10)
        a.io.out.expect("b1010".U)
    //   b.io.out.expect(0.B)
    //   b.io.data_in.poke(5.U)

    }
  }
}


