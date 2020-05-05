import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleTypePageComponent } from './vehicle-type-page.component';

describe('VehicleTypePageComponent', () => {
  let component: VehicleTypePageComponent;
  let fixture: ComponentFixture<VehicleTypePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleTypePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleTypePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
