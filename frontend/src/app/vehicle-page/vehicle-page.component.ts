import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router ,NavigationEnd} from '@angular/router';
import {ApiService} from '../api.service'
import {Observable} from 'rxjs/Rx';
import { switchMap } from 'rxjs/operators';


export interface Vehicle {
  id:number;
  VehicleType: string;
  model: string;
  make: string;
  year: string;
  vin:string;
  licensePlate:string;
  location:string;
  vehicleCondition:string;
}


@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.scss']
})
export class VehiclePageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'VehicleType', 'model','make','year','licensePlate', 'vin','location','vehicleCondition','star'];
  dataSource: MatTableDataSource<Vehicle>;
  ELEMENT_DATA: Vehicle[] = [];
 @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
 @ViewChild(MatSort, {static: true}) sort: MatSort;
 isLoadingResults = false;
 plan$: Observable<any>;

 constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {

console.log(this.actr.data)
 // this.plan$ = this.actr.data.vehicle.pipe(switchMap((data) => {
 //   console.log(data)
 //   data.vehicle$
 // }));
  //this.actr.data.subscribe((data)=>{
  //  this.actr.data.pipe(switchMap(d.subscribe(da=>{ console.log(da)}))) ;
 this.actr.data.subscribe((data) => {
    console.log(data);
    var vehicles = data.vehicle;
    console.log(vehicles)
    console.log(vehicles.length)

    var i = 0;
    for (i = 0; i < vehicles.length; i++) {

      var obj = vehicles[i];
      console.log(obj)
      var vehicle = {
        id: obj.id,
        VehicleType:obj.vehicleTypeId.vehicleClass,
        model: obj.model,
         make:obj.make,
         year:obj.year,
         vin:obj.vin,
         licensePlate:obj.licensePlate,
         location:obj.location.name,
         vehicleCondition:obj.vehicleCondition
      }
      this.ELEMENT_DATA[i] = vehicle;
    }
  });

   this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);





 }


 ngOnInit() {
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
updateVehicle(operation, ele){
  console.log('update ');
  console.log(ele)
  if(operation == 'Update') {
    this.router.navigate(['/editVehicle/'+ele.id]);
  }
  if(operation == 'Delete') {
    var formData = {}
    formData['id'] = ele.id;
    this.isLoadingResults = false;
    this.api.deleteVehicle(formData).subscribe((res:any) => {
      console.log(res);
        this.isLoadingResults = false;

        //this.router.navigate(['/vehicle']);

        this.actr.data.subscribe((data) => {
           console.log(data);
           var vehicles = data.vehicle;
           console.log(vehicles)
           console.log(vehicles.length)

           var i = 0;
           for (i = 0; i < vehicles.length; i++) {

             var obj = vehicles[i];
             console.log(obj)
             var vehicle = {
               id: obj.id,
               VehicleType:obj.vehicleTypeId.vehicleClass,
               model: obj.model,
                make:obj.make,
                year:obj.year,
                vin:obj.vin,
                licensePlate:obj.licensePlate,
                location:obj.location.name,
                vehicleCondition:obj.vehicleCondition
             }
             this.ELEMENT_DATA[i] = vehicle;
           }
         });

          this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);


      });

      location.reload();
    //this.router.navigate(['/editVehicle/'+ele.id]);
  }


}


}
