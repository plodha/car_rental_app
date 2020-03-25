from src.model import Reservation

class Invoice:
    # attributes:
    _id = 0
    estimatedPrice = 0.0
    vehicleStatus = 'good'
    damageFee = 0.0
    lateFee = 0.0
    totalFee = 0.0
    reservation = None #Reservation

    #constructor
    def __init__(self):
        self._id = 0


