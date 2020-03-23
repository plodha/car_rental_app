from Model import Location, Vehicle, Customer, Invoice

class Reservation:
    # attributes:
    _id = 0
    estimatePrice = 0.0
    pickupTime = '12.00PM'
    estimateDropoffTime = '12.00PM'
    actualDropoffTime = '12.00PM'
    location = Location()
    vehicle = Vehicle()
    customer = Customer()
    invoice = Invoice()


   #constructor
    def __init__(self):
        self.name = ''
