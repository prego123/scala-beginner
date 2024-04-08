//package lectures.oops
//
//object Enum {
//  // created sealed data type that cannot be extended and th only possible these constants
//  enum Permissions {
//    case READ, WRITE, EXECUTE, NONE
//
//    // add fields/methods
//    def openDocument(): Unit = {
//      if (this == READ) println("opening documents....")
//      else println("reading not allowed......")
//    }
//  }
//
//  val somePermissions: Permissions = Permissions.READ
//
//
//  // constructor arguments
//  enum PermissionsWithBits(bits: Int) {
//    case READ extends PermissionsWithBits(4) // 100
//    case WRITE extends PermissionsWithBits(2) // 010
//    case EXECUTE extends PermissionsWithBits(1) // 001
//    case NONE extends PermissionsWithBits(0) // 000
//  }
//
//  // companion objects for object
//  object PermissionsWithBits {
//    def fromBits(bits: Int): PermissionsWithBits =  // whatever
//      PermissionsWithBits.NONE
//  }
//
//  // standard API
//  val somePermissionsOrdinal = somePermissions.ordinal
//
//  // to iterate or get a hold of all the possible values
//  val allPermission = PermissionsWithBits.values // array of all possible values of the enum
//
//  // create instance of enum from string
//  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ
//
//  def main(args: Array[String]): Unit = {
//    somePermissions.openDocument()
//    println(somePermissionsOrdinal)
//  }
//}
//
///*
//  enums can pretty much work like any class
//  except you already have the constants of that class already
//*/
