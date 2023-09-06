package Lab6



//import chiseltest._
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task3Tester extends FreeSpec with ChiselScalatestTester {
  "lab 6 task 3 Tester file" in {
    test(new shift_reg_with_parallel_load){a =>
        // a.io.up_down.poke(0.B)
        //a.io.in.poke(1.B)
        a.io.load.poke(1.B)
        a.io.load_in(0).poke(1.B)
        a.io.load_in(1).poke(1.B)
        a.io.load_in(2).poke(1.B)
        //a.io.load_in(2).poke(1.B)

        a.clock.step()
        a.io.out(0).expect(1.B)
        a.io.out(1).expect(1.B)
        a.io.out(2).expect(1.B)

        // a.io.out.expect("b1010".U)
    //   b.io.out.expect(0.B)
    //   b.io.data_in.poke(5.U)

    }
  }
}




