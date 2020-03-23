from . import Address


class Customer:
    # attributes:
    name = ''
    licenseNumber = ''
    registrationDate = '01-01-2020'
    registrationEndDate = '01-01-2020'
    membershipStatus = True
    verified = True
    licenseExpDate = '01-01-2020'
    # address: Address
    email = 'car-rent@gmail.com'
    _id = 0

   #constructor
    def __init__(self):
        self.name = ''
        self.address = Address()

