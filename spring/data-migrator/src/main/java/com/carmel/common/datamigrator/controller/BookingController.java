package com.carmel.common.datamigrator.controller;

import com.carmel.common.datamigrator.model.Booking;
import com.carmel.common.datamigrator.model.BookingAdditionalCharge;
import com.carmel.common.datamigrator.model.Inventory;
import com.carmel.common.datamigrator.model.Package;
import com.carmel.common.datamigrator.repository.BookingAdditionalChargeRepository;
import com.carmel.common.datamigrator.repository.BookingRepository;
import com.carmel.common.datamigrator.repository.InventoryRepository;
import com.carmel.common.datamigrator.repository.PackageRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(value = "/migration/booking")
public class BookingController {

    private static final String FILE_NAME = "/Users/pete/Desktop/WIP/Guest/Migration/AltaVista-Booking.xlsx";
    private static final String ALTA_VISTA = "dd019bb3-be78-48e9-9039-70281031c719";

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    BookingAdditionalChargeRepository bookingAdditionalChargeRepository;

    @RequestMapping("/import-excel")
    public void importBooking() {
        try {
            Integer CHECK_IN_DATE_COL_NO = null;
            Integer POD_NO_COL_NO = null;
            Integer ROOM_NO_COL_NO = null;
            Integer FLAT_COL_NO = null;
            Integer FLOOR_COL_NO = null;
            Integer BLOCK_COL_NO = null;
            Integer FULL_NAME_COL_NO = null;
            Integer GENDER_COL_NO = null;
            Integer PACKAGE_COL_NO = null;
            Integer POD_PRICE_COL_NO = null;
            Integer UTILITY_CHARGES_COL_NO = null;
            Integer SECURITY_DEPOSIT_COL_NO = null;
            Integer PHONE_NO_COL_NO = null;
            Integer EMAIL_ID_COL_NO = null;
            Integer COMPANY_COL_NO = null;
            Integer DISCOUNT_COL_NO = null;
            int i = 0;
            int rowCount = 0;

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                if (rowCount == 0) {
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        switch (currentCell.getStringCellValue().toLowerCase().trim()) {
                            case "check in date": {
                                CHECK_IN_DATE_COL_NO = i;
                            }
                            break;
                            case "pod no": {
                                POD_NO_COL_NO = i;
                            }
                            case "floor": {
                                FLOOR_COL_NO = i;
                            }
                            break;
                            case "room": {
                                ROOM_NO_COL_NO = i;
                            }
                            break;
                            case "suite": {
                                FLAT_COL_NO = i;
                            }
                            break;
                            case "block": {
                                BLOCK_COL_NO = i;
                            }
                            break;
                            case "name": {
                                FULL_NAME_COL_NO = i;
                            }
                            break;
                            case "gender": {
                                GENDER_COL_NO = i;
                            }
                            break;
                            case "payment package": {
                                PACKAGE_COL_NO = i;
                            }
                            break;
                            case "pod price": {
                                POD_PRICE_COL_NO = i;
                            }
                            break;
                            case "utility charges": {
                                UTILITY_CHARGES_COL_NO = i;
                            }
                            break;
                            case "security deposit": {
                                SECURITY_DEPOSIT_COL_NO = i;
                            }
                            break;
                            case "phone no": {
                                PHONE_NO_COL_NO = i;
                            }
                            break;
                            case "email id": {
                                EMAIL_ID_COL_NO = i;
                            }
                            break;
                            case "company": {
                                COMPANY_COL_NO = i;
                            }
                            case "discount": {
                                DISCOUNT_COL_NO = i;
                            }
                            break;
                        }
                        i++;
                    }
                    rowCount++;
                } else {
                    try {
                        String email = currentRow.getCell(EMAIL_ID_COL_NO).getStringCellValue().trim();
                        if (!email.equals("")) {
                            Optional<Booking> optionalBooking = bookingRepository.findByEmailAndBookingStatus(email, 1);
                            Booking booking;
                            if (!optionalBooking.isPresent()) {
                                booking = new Booking();
                                booking.setReferenceNo(String.valueOf(System.nanoTime()));
                                booking.setBookingStatus(1);
                                booking.setFullName(currentRow.getCell(FULL_NAME_COL_NO).getStringCellValue().trim());
                                Integer gender = 0;
                                if (currentRow.getCell(GENDER_COL_NO).getStringCellValue().toLowerCase().trim().equals("m")) {
                                    gender = 1;
                                } else if (currentRow.getCell(GENDER_COL_NO).getStringCellValue().toLowerCase().trim().equals("f")) {
                                    gender = 2;
                                }
                                booking.setGender(gender);
                                if (currentRow.getCell(PHONE_NO_COL_NO).getCellType() == CellType.NUMERIC) {
                                    Double doubleValue = currentRow.getCell(PHONE_NO_COL_NO).getNumericCellValue();
                                    BigDecimal bd = new BigDecimal(doubleValue.toString());
                                    long lonVal = bd.longValue();
                                    String phoneNumber = Long.toString(lonVal).trim();
                                    booking.setPhone(phoneNumber);

                                } else {
                                    booking.setPhone(currentRow.getCell(PHONE_NO_COL_NO).getStringCellValue().trim());

                                }
                                booking.setEmail(currentRow.getCell(EMAIL_ID_COL_NO).getStringCellValue().trim());
                                booking.setCheckIn(currentRow.getCell(CHECK_IN_DATE_COL_NO).getDateCellValue());
                                booking.setCheckInTime(currentRow.getCell(CHECK_IN_DATE_COL_NO).getDateCellValue());
                                booking.setCheckOut(new Date());
                                booking.setCheckOutTime(new Date());
                                booking = bookingRepository.save(booking);
                            } else {
                                booking = optionalBooking.get();
                                String blockName = currentRow.getCell(BLOCK_COL_NO).getStringCellValue().trim();
                                if (!blockName.equals("")) {
                                    Optional<Inventory> optionalBlock = inventoryRepository
                                            .findByTitleAndParentIdAndIsDeleted(
                                                    blockName,
                                                    ALTA_VISTA,
                                                    0
                                            );
                                    if (optionalBlock.isPresent()) {
                                        String floorName = currentRow.getCell(FLOOR_COL_NO).getStringCellValue().trim();
                                        if (!floorName.equals("")) {
                                            Optional<Inventory> optionalFloor = inventoryRepository
                                                    .findByTitleAndParentIdAndIsDeleted(
                                                            floorName,
                                                            optionalBlock.get().getId(),
                                                            0
                                                    );
                                            if (optionalFloor.isPresent()) {
                                                String flatName = currentRow.getCell(FLAT_COL_NO).getStringCellValue().trim();
                                                if (!flatName.equals("")) {
                                                    Optional<Inventory> optionalFlat = inventoryRepository
                                                            .findByTitleAndParentIdAndIsDeleted(
                                                                    flatName,
                                                                    optionalFloor.get().getId(),
                                                                    0
                                                            );
                                                    if (optionalFlat.isPresent()) {
                                                        String roomName = currentRow.getCell(ROOM_NO_COL_NO).getStringCellValue().trim();
                                                        if (!roomName.equals("")) {
                                                            Optional<Inventory> optionalRoom = inventoryRepository
                                                                    .findByTitleAndParentIdAndIsDeleted(
                                                                            roomName,
                                                                            optionalFlat.get().getId(),
                                                                            0
                                                                    );

                                                            if (optionalRoom.isPresent()) {
                                                                String podName = currentRow.getCell(POD_NO_COL_NO).getStringCellValue().trim();
                                                                if (!podName.equals("")) {
                                                                    Optional<Inventory> optionalPod = inventoryRepository
                                                                            .findByTitleAndParentIdAndIsDeleted(
                                                                                    podName,
                                                                                    optionalRoom.get().getId(),
                                                                                    0
                                                                            );
                                                                    if (optionalPod.isPresent()) {
                                                                        booking.setInventoryId(optionalPod.get().getId());

                                                                    } else {
                                                                        booking.setInventoryId(optionalRoom.get().getId());
                                                                    }
                                                                } else {
                                                                    booking.setInventoryId(optionalRoom.get().getId());
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                bookingRepository.save(booking);
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        } catch (Exception ex) {
            String str = ex.getMessage();
        }
    }

    @RequestMapping("/assign-package")
    public void assignPackage() {
        try {
            Integer CHECK_IN_DATE_COL_NO = null;
            Integer POD_NO_COL_NO = null;
            Integer ROOM_NO_COL_NO = null;
            Integer FLAT_COL_NO = null;
            Integer FLOOR_COL_NO = null;
            Integer BLOCK_COL_NO = null;
            Integer FULL_NAME_COL_NO = null;
            Integer GENDER_COL_NO = null;
            Integer PACKAGE_COL_NO = null;
            Integer POD_PRICE_COL_NO = null;
            Integer UTILITY_CHARGES_COL_NO = null;
            Integer SECURITY_DEPOSIT_COL_NO = null;
            Integer PHONE_NO_COL_NO = null;
            Integer EMAIL_ID_COL_NO = null;
            Integer COMPANY_COL_NO = null;
            Integer DISCOUNT_COL_NO = null;
            int i = 0;
            int rowCount = 0;
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                if (rowCount == 0) {
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        switch (currentCell.getStringCellValue().toLowerCase().trim()) {
                            case "check in date": {
                                CHECK_IN_DATE_COL_NO = i;
                            }
                            break;
                            case "pod no": {
                                POD_NO_COL_NO = i;
                            }
                            case "floor": {
                                FLOOR_COL_NO = i;
                            }
                            break;
                            case "room": {
                                ROOM_NO_COL_NO = i;
                            }
                            break;
                            case "suite": {
                                FLAT_COL_NO = i;
                            }
                            break;
                            case "block": {
                                BLOCK_COL_NO = i;
                            }
                            break;
                            case "name": {
                                FULL_NAME_COL_NO = i;
                            }
                            break;
                            case "gender": {
                                GENDER_COL_NO = i;
                            }
                            break;
                            case "payment package": {
                                PACKAGE_COL_NO = i;
                            }
                            break;
                            case "pod price": {
                                POD_PRICE_COL_NO = i;
                            }
                            break;
                            case "utility charges": {
                                UTILITY_CHARGES_COL_NO = i;
                            }
                            break;
                            case "security deposit": {
                                SECURITY_DEPOSIT_COL_NO = i;
                            }
                            break;
                            case "phone no": {
                                PHONE_NO_COL_NO = i;
                            }
                            break;
                            case "email id": {
                                EMAIL_ID_COL_NO = i;
                            }
                            break;
                            case "company": {
                                COMPANY_COL_NO = i;
                            }
                            break;
                            case "discount": {
                                DISCOUNT_COL_NO = i;
                            }
                            break;
                        }
                        i++;
                    }
                    rowCount++;
                } else {
                    try {
                        String email = currentRow.getCell(EMAIL_ID_COL_NO).getStringCellValue().trim();
                        if (!email.equals("")) {
                            Optional<Booking> optionalBooking = bookingRepository.findByEmailAndBookingStatus(email, 1);
                            Booking booking;
                            if (optionalBooking.isPresent()) {
                                booking = optionalBooking.get();
                                Optional<Inventory> optionalInventory =
                                        inventoryRepository.findById(booking.getInventoryId());
                                if (optionalInventory.isPresent()) {
                                    Inventory inventory = optionalInventory.get();
                                    List<Package> packageList = inventory.getPackages();
                                    if (packageList == null) {
                                        packageList = new ArrayList<>();
                                    }
                                    String packageName = currentRow.getCell(PACKAGE_COL_NO).getStringCellValue().trim();
                                    if (!packageName.equals("")) {
                                        double utilityBill = currentRow.getCell(UTILITY_CHARGES_COL_NO).getNumericCellValue();
                                        double discountValue = currentRow.getCell(DISCOUNT_COL_NO).getNumericCellValue();
                                        Optional<Package> optionalPackage =
                                                packageRepository
                                                        .findByPackageTitleAndIsDeleted(packageName, 0);
                                        if (optionalPackage.isPresent()) {
                                            Optional<Package> foundPackage = packageList.stream().filter(
                                                    ap -> ap.getPackageTitle().equals(
                                                            optionalPackage.get().getPackageTitle())
                                            ).findFirst();
                                            if (!foundPackage.isPresent()) {
                                                packageList.add(optionalPackage.get());
                                                inventory.setPackages(packageList);
                                                inventoryRepository.save(inventory);
                                            }
                                            booking.setPackageId(optionalPackage.get().getId());
                                            booking.setRent(optionalPackage.get().getRent());
                                            booking.setRentUnit(optionalPackage.get().getRentCycle());
                                            BookingAdditionalCharge bookingAdditionalCharge = new BookingAdditionalCharge();
                                            bookingAdditionalCharge.setTitle("Utility Bill");
                                            bookingAdditionalCharge.setAmount(utilityBill);
                                            bookingAdditionalCharge.setChargeMethod(1);//INVOICE
                                            bookingAdditionalCharge.setBillingCycle(1);//MONTH
                                            bookingAdditionalCharge.setClientId("21e43c55-28ef-478a-ae65-dc896e5eaa34");
                                            bookingAdditionalCharge.setBooking(booking);
                                            bookingAdditionalCharge.setPackageId(optionalPackage.get().getId());
                                            bookingAdditionalCharge.setIsDeleted(0);
                                            bookingAdditionalChargeRepository.save(bookingAdditionalCharge);
                                            booking.setDiscountValue(discountValue);
                                            booking.setDiscountValueIdentifier(1);//FIXED
                                            booking.setDiscountIdentifier(2);//RECCURRING
                                            bookingRepository.save(booking);
                                        } else {
                                            throw new Exception("Package not found");
                                            //TODO: Check Why
                                        }
                                    } else {
                                        throw new Exception("Package name not found");
                                        //TODO: Check Why
                                    }

                                } else {
                                    throw new Exception("Inventory not assigned");
                                    //TODO: Assign Inventory!
                                }
                            }
                        }
                    } catch (Exception ex) {
                        String str = ex.getMessage();
                    }
                }
            }
        } catch (Exception ex) {
            String str = ex.getMessage();
        }
    }
}
