from . import Address

class Location:
    # attributes:
    _id = 0
    name = ''
    vehicleCapacity = 0
    #address: Address
    concatNumber = ''

    # constructor
    def __init__(self):
        self._id = 0
        self.address = Address()