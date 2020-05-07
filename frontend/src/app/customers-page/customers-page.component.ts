import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router } from '@angular/router';

import {ApiService} from '../api.service'

export interface Customer {
  id:number;
  name: string;
  addressLine: string;
  licenseInfo: string;
  licenseExpDate:string;
  startDate: string;
  endDate:string;
  status:string;
  email :string;
  userId:number;

}

@Component({
  selector: 'app-customers-page',
  templateUrl: './customers-page.component.html',
  styleUrls: ['./customers-page.component.scss']
})
export class CustomersPageComponent implements OnInit {
  isLoadingResults = false;
  displayedColumns: string[] = ['id', 'name', 'addressLine','licenseInfo','licenseExpDate','startDate','endDate','email','star'];
  dataSource: MatTableDataSource<Customer>;
  ELEMENT_DATA: Customer[] = [];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
    this.actr.data.subscribe((data)=>{
      console.log(data);
      var customers = data.customer;
      console.log(customers)
      console.log(customers.length)

      var i = 0;
      for (i = 0; i < customers.length; i++) {

        var obj = customers[i];
        console.log(obj)
        var customer = {
          id: obj.id,
          name:obj.firstName+' '+obj.lastName,
          addressLine: obj.address.street+', '+obj.address.city+", "+obj.address.state+", "+obj.address.zipCode,
           licenseInfo:obj.licenseNumber,
           licenseExpDate:obj.licenseExpDate,
           startDate:obj.membershipStartDate,
           endDate:obj.membershipEndDate,
            email:obj.email,
            userId : obj.userId.id,
            status : 'Active'
        }
        this.ELEMENT_DATA[i] = customer;
      }
    });
    this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);

  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    console.log(localStorage.username != undefined)
if(localStorage.username != undefined ) {
  document.getElementById('profileName').innerHTML += localStorage.username
}
else {
  this.router.navigate(['/login']);

}
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  terminateCustomer(operation, ele){
    console.log(ele)

    if(operation == 'Delete') {
      var formData = {}
      console.log(ele.userId)
      formData['userId'] = ele.userId;
      this.isLoadingResults = false;
      this.api.cancelMembershipAPI(formData).subscribe((res:any) => {
        console.log(res);
          this.isLoadingResults = false;

          //this.router.navigate(['/vehicle']);
          location.reload();

        });


      //this.router.navigate(['/editVehicle/'+ele.id]);
    }


  }
}
