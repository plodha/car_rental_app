from . import VehicleType


class Vehicle:
    # attributes:
    _id = 0
    locationa = ''
    status = ''  # available - booked?
#vehicleType: VehicleType
    make = 0 # don't get it?
    model = ''
    year = ''
    VIN = ''
    licensePlate = ''

#constructor
    def __init__(self):
        self._id = 0
        self.vehicleType = VehicleType()


