import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import {ActivatedRoute, Router } from '@angular/router';


import {ApiService} from '../api.service'

export interface Price {
  id:number;
  vehicleClass: string;
  vehicleSize: string;
  lateFee: string;
  hourlyFee: string;
  hourlyRange:string;
  vehicleTypeId: number;


}
@Component({
  selector: 'app-price-page',
  templateUrl: './price-page.component.html',
  styleUrls: ['./price-page.component.scss']
})
export class PricePageComponent implements OnInit {

  displayedColumns: string[] = ['id', 'vehicleClass', 'vehicleSize','lateFee','hourlyFee','hourlyRange','star'];
  dataSource: MatTableDataSource<Price>;
  ELEMENT_DATA: Price[] = [];
  isLoadingResults = false;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;


  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
    this.actr.data.subscribe((data)=>{
      var prices = data.price;
      var i = 0;
      for (i = 0; i < prices.length; i++) {

        var obj = prices[i]
        var price = {
          id: obj.id,
          vehicleClass:obj.vehicleTypeId.vehicleClass,
          vehicleSize: obj.vehicleTypeId.vehicleSize,
          lateFee : obj.lateFee,
          hourlyFee :obj.hourlyPrice,
          hourlyRange:obj.hourlyRange,
          vehicleTypeId : obj.vehicleTypeId.id

        }
        this.ELEMENT_DATA[i] = price;
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

  updatePrice(operation, ele){
    console.log('update ');
    console.log(ele)
    if(operation == 'Update') {
      this.router.navigate(['/editPrice/'+ele.id]);
    }
    if(operation == 'Delete') {
      var formData = {}
      formData['priceId'] = ele.id;
      console.log(ele.id)

      this.isLoadingResults = false;
      this.api.deletePrice(formData).subscribe((res:any) => {
          console.log(res);
          this.isLoadingResults = false;

          //this.router.navigate(['/vehicle']);

          location.reload();


        });

        location.reload();
      //this.router.navigate(['/editVehicle/'+ele.id]);
    }


  }

}
