import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLocationPageComponent } from './add-location-page.component';

describe('AddLocationPageComponent', () => {
  let component: AddLocationPageComponent;
  let fixture: ComponentFixture<AddLocationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLocationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLocationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
